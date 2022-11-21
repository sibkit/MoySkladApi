package reports;

import moysklad.core.MsDataSet;

public interface ReportMaker {
    void initialize(MsDataSet dataSet);
    void print();
}
