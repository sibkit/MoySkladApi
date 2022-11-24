package reports;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeIdentifier
{
    static List<String> finishedProducts;

    static void initialize()
    {
        finishedProducts = new ArrayList<>();
        finishedProducts.add("светильник");
        finishedProducts.add("Лампа");
        finishedProducts.add("Блок индикации");
    }

    public static ProcessingResult identify(String productName, String processingPlanName)
    {
        if(finishedProducts==null)
        {
            initialize();
        }

        if(processingPlanName.contains("Ремонт") || processingPlanName.contains("РЕМОНТ"))
            return ProcessingResult.REPAIR;
        else
        {
            for(String fp : finishedProducts)
            if (productName.contains(fp))
                return ProcessingResult.FINISHED;

            return ProcessingResult.SEMIFINISHED;
        }

    }
}
