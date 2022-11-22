package com.gwtsas.prestacarro.services.impl;

import com.gwtsas.prestacarro.entities.Loan;
import com.gwtsas.prestacarro.services.ReportGenerator;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoansReportGeneratorImpl implements ReportGenerator<Loan> {

    static final String IMAGE_LOGO_PATH = "assets/images/logo_footer.png";
    static final String REPORT_PATH = "assets/reports/report.jrxml";

    @Override
    public void generateReport(List beanList, OutputStream outputStream) throws JRException, IOException {

        Map<String, Object> parameters = new HashMap<>();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanList);

        parameters.put("title", "Report Example");
        parameters.put("startDate", new Date());
        parameters.put("endDate", new Date());
        parameters.put("generator", "ADMIN");
        parameters.put("loansDataSource", beanCollectionDataSource);

        BufferedImage image  = ImageIO.read(new FileInputStream(new File(IMAGE_LOGO_PATH)));

        parameters.put("logo", image);

        JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream(new File(REPORT_PATH)));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }

}
