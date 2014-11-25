package m2.project.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import m2.project.model.Category;
import m2.project.model.ErrorMessage;
import m2.project.model.JsonResponse;


import m2.project.service.CategoryService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String categorysList(Model model,@ModelAttribute Category category){//,Pageable pageable,@ModelAttribute Category cat) {
		// for the customers list
		//final PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), 5, Direction.ASC, "name");
		
		model.addAttribute("categorys", categoryService.findAll());
		
		return "/category/listcategory";
		
		
	
		
	}
	

	// Submit create / edit  form - Ajax 
	@RequestMapping(value = "/category", method = RequestMethod.POST, produces={"application/json"})
	@ResponseBody
	public JsonResponse ajaxSubmitCategoryForm(Model model, @ModelAttribute(value = "category") @Valid Category category, BindingResult result) {
		JsonResponse res = new JsonResponse();
		if (!result.hasErrors()) {
		

			if(!(categoryService.findOne(category.name).isEmpty()))
				categoryService.save(category);
			res.setStatus("SUCCESS");

			
			try {
				categoryService.save(category);
				res.setStatus("SUCCESS");
			}
			catch(Exception e) {
				res.setStatus("FAIL");
			}
		}
		else {
			res.setStatus("FAIL");
			List<FieldError> allErrors = result.getFieldErrors();
			List<ErrorMessage> errorMesages = new ArrayList<ErrorMessage>();
			for (FieldError objectError : allErrors) {
				errorMesages.add(new ErrorMessage(objectError.getField(), objectError.getDefaultMessage()));
			}
			res.setErrorMessageList(errorMesages);
		}
		
		return res;
	}
	
	
	@RequestMapping(value = "/category/edit", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Category ajaxEditCategoryForm(@RequestParam("id") Long id) {
		return categoryService.findOne(id);
	}
	
	@RequestMapping(value = "/category/delete", method = RequestMethod.GET)
	public String deleteCategory(@RequestParam("id") Long id, Model model) {
		categoryService.delete(id);
		return "redirect:/category";
	}
	
	
}
