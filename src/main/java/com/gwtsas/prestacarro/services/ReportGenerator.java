package com.gwtsas.prestacarro.services;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public interface ReportGenerator<T> {

    void generateReport(List<T> beanList, OutputStream outputSteam) throws JRException, IOException;
}
