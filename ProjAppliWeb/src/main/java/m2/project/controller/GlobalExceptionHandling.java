package m2.project.controller;

import java.sql.SQLException;
import java.util.Date;

import javassist.expr.Instanceof;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
public class GlobalExceptionHandling {
	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public ModelAndView databaseError(HttpServletRequest req, Exception exception) throws Exception {
		ModelAndView mav = new ModelAndView();
		//mav.addObject("exception", exception);
		//mav.addObject("url", req.getRequestURL());
		mav.addObject("retUrl", req.getHeader("referer"));
		//mav.addObject("timestamp", new Date().toString());
		if (exception instanceof DataIntegrityViolationException) {
			mav.addObject("message", "Cet élément ne peut pas être supprimé, car il est en lien avec d'autres");
		}
		else {
			mav.addObject("message", "Problème avec la base de données");
		}
		//mav.addObject("status", 500);
		mav.setViewName("/errors/error");
		return mav;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) throws Exception {
		if (AnnotationUtils.findAnnotation(exception.getClass(), ResponseStatus.class) != null)
			throw exception;
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("retUrl", req.getHeader("referer"));
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);
		mav.setViewName("/errors/error");
		return mav;
	}
	
	/*
	// 409
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void conflict() {
		System.out.println();
	}

	// 403
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Pas les droits")
	@ExceptionHandler(AccessDeniedException.class)
	public void accessDenied() {
		System.out.println();
	}
	 */
	
	/*
	@ExceptionHandler(EmployeeNotFoundException.class)
	public @ResponseBody ExceptionJSONInfo handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
	     
	    ExceptionJSONInfo response = new ExceptionJSONInfo();
	    response.setUrl(request.getRequestURL().toString());
	    response.setMessage(ex.getMessage());
	     
	    return response;
	}
	 */
}