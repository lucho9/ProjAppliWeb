package m2.project.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import m2.project.model.Category;
import m2.project.model.Customer;
import m2.project.model.Facture;
import m2.project.repository.CustomerRepository;
import m2.project.repository.FactureRepository;
import m2.project.service.CustomerService;
import m2.project.service.FactureService;
import m2.project.service.ProductService;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller
public class ChartController {
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private FactureService factureService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private ProductService productService;
	

	

	@RequestMapping(value="/stats",method=RequestMethod.GET)
	public String affichePage(){
		return "/stats/statsForm";
	}
	
	
	
	@RequestMapping(value="/stats/chart3",method=RequestMethod.GET)
	public void drawLineChart(HttpServletResponse response){
		response.setContentType("image/png");
		CategoryDataset  pdSet3=createDataset3();
		JFreeChart lineChart = ChartFactory.createLineChart3D("Nombre de factures enregistrées par date", "", "Date", pdSet3,PlotOrientation.VERTICAL, false, false, false);
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(),lineChart,750,400);
			
			
			//response.getOutputStream().close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	
	}
	

	
	@RequestMapping(value="/stats/chart2",method=RequestMethod.GET)
	public void drawBarChart(HttpServletResponse response){
		response.setContentType("image/png");
		CategoryDataset pdSet2=createDataset2();
		JFreeChart barChart = ChartFactory.createBarChart("Dépense de  chaque client", "Montant des dépenses", "Euros", pdSet2,PlotOrientation.VERTICAL, false, false, false);
		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(),barChart,750,400);
			
			
			//response.getOutputStream().close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/stats/chart",method=RequestMethod.GET)
	public void drawPieChart(HttpServletResponse response){
		response.setContentType("image/png");


		
		
		//JFreeChart barChart = ChartFactory.createBarChart("   ", "Category", "Score", createDataset(),PlotOrientation.VERTICAL, true, true, false);
		
		
		PieDataset pdSet=createDataSet();
		JFreeChart chart=createChart(pdSet,"Moyens de paiements les plus utilisés");
		
		try{
			ChartUtilities.writeChartAsPNG(response.getOutputStream(),chart,750,400);
			
			
			//response.getOutputStream().close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

	private JFreeChart createChart(PieDataset pdSet, String CharTitle) {
		JFreeChart chart=ChartFactory.createPieChart3D(CharTitle, pdSet, true, true, true);
		PiePlot3D plot=(PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		return chart;
	
	
	
	}

	private PieDataset createDataSet() {
		DefaultPieDataset dpd=new DefaultPieDataset();
		double CB = 0,cheque = 0,liquide = 0;
		List<Integer> moyen=new ArrayList<Integer>();
		List<Facture>  lf=factureService.findAll();
		for(int i=0;i<lf.size();i++){
			if(lf.get(i).getMoyenPaiement() == "Espèces")
			{
				liquide++ ;
			}
			else
			if(lf.get(i).getMoyenPaiement() == "Chèque")
			{
			cheque++;
			}
			else
			if(lf.get(i).getMoyenPaiement() == "Carte-bancaire")
			{
				CB++;
			}
		}
		
		
		double total=CB+cheque+liquide;
		
		double CBfinal=(CB/total);
		CBfinal=CBfinal*100;
		double chequefinal=(cheque/total);
		chequefinal=chequefinal*100;
		double liquidefinal=(liquide/total);
		liquidefinal=liquidefinal*100;
		
		dpd.setValue("Par CB", CBfinal);
		dpd.setValue("Par chèque", chequefinal);
		dpd.setValue("En liquide", liquidefinal);
		
		return dpd;
	}
	
	
	
	   private CategoryDataset createDataset2() {
		     
		   
		   	List<Double> depense=new ArrayList<Double>();
		    List<Customer> lc=customerService.findAll();
		    List<Facture>  lf=factureService.findAll();
		   List<Category> listCat;

		   
		    for(int i=0;i<lc.size();i++){
		    depense.add(i, customerService.totalDepense(lc.get(i)));
		    }
		   
		    
		    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		    
		    for(int i=0;i<lc.size();i++){
		    	 dataset.addValue(depense.get(i), "",lc.get(i).getFirstName());
			    }

		      return dataset;
		     
		  }
	   
	   
	   private CategoryDataset  createDataset3() {
		   // create the dataset...
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		   

	        List l=factureService.getTotalFacture();
	        
	        
	       // DatasetUtilities.createCategoryDataset("total","totalFacture", l)
	        Long lo=(Long)((Object[]) l.get(0))[0];
	        String date=new Date(((Timestamp)((Object[]) l.get(0))[1]).getTime()).toString();
	        
	        for(int i=0;i<l.size();i++){
	        dataset.addValue((Long)((Object[]) l.get(i))[0], "cat", new Date(((Timestamp)((Object[]) l.get(i))[1]).getTime()).toString());
	        }
	        
	        return dataset;
		  }
	
}
