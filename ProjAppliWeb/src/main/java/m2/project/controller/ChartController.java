package m2.project.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ChartController {
	
	
	

	@RequestMapping(value="/stats",method=RequestMethod.GET)
	public String affichePage(){
		return "/stats/statsForm";
	}

	
	@RequestMapping(value="/chart",method=RequestMethod.GET)
	public void drawPieChart(HttpServletResponse response){
		response.setContentType("image/png");
		PieDataset pdSet=createDataSet();
		JFreeChart chart=createChart(pdSet,"Mon Graphique");
		
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
		dpd.setValue("MAC", 21);
		
		dpd.setValue("Linux", 30);
		dpd.setValue("Window", 40);
		dpd.setValue("Others", 9);
		return dpd;
	}
	
	
}
