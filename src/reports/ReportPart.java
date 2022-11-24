package reports;

import java.util.ArrayList;
import java.util.List;

public class ReportPart
{
    private int year;
    private int month;
    private List<GroupItem> finishedItems = new ArrayList<>();
    private List<GroupItem> semifinishedItems = new ArrayList<>();
    private GroupItem repairsItems = new GroupItem();
    public int getYear()
    {
        return year;
    }
    public void setYear(int year)
    {
        this.year = year;
    }
    public int getMonth()
    {
        return month;
    }
    public void setMonth(int month)
    {
        this.month = month;
    }
    public List<GroupItem> getFinishedItems()
    {
        return finishedItems;
    }
    public List<GroupItem> getSemifinishedItems()
    {
        return semifinishedItems;
    }
    public GroupItem getRepairsItems()
    {
        return repairsItems;
    }
}
