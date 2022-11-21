package moysklad.aa_old;

import moysklad.aa_old.MsDataManager_old;
import moysklad.entities.common.MsStore;
import moysklad.entities.production.MsProcessing;
import moysklad.entities.production.MsProcessingOrder;
import moysklad.entities.purchases.MsPurchaseOrder;
import moysklad.entities.purchases.MsSupply;
import moysklad.entities.sales.MsCustomerOrder;
import moysklad.entities.sales.MsDemand;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StoreUpdater
{
    private List<MsStore> stores;
    private MsDataManager_old dataManager;
    private MsStore trueStore;
    private List<String> codes = new ArrayList<>();

    private MsStore getStore(UUID storeId)
    {
        for(MsStore store: stores)
            if(store.getId().equals(storeId))
                return store;
        return null;
    }

    private Boolean checkStore(MsStore store)
    {
        if(store.getName().contains("НЕ ИСПОЛЬЗОВАТЬ"))
            return false;
        return true;
    }

    public void updateStores()
    {
        long t1 = System.nanoTime();

        dataManager= MsDataManager_old.getInstance();
        stores = dataManager.loadAllEntities(MsStore.class);

        try
        {
            for (MsStore st : stores)
            {
                if (st.getName().equals("Основной склад"))
                {
                    trueStore = st;
                    break;
                }
            }

            if (trueStore == null) return;

            //updatePurchaseOrders();
            //updateCustomerOrders();
            //updateSupplies();
            //updateDemands();
            updateProcessingOrders();
            updateProcessing();

            long t2 = System.nanoTime();
            System.out.println();
            System.out.println("Updating completed! (time: " + (t2 - t1) / 1_000_000 + "s.)");
            System.out.println("Total objects updated: " + codes.size() + ":");
            for (String code : codes)
                System.out.println(code);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("Updating failed!");
            System.out.println("Total objects updated: " + codes.size() + ":");
            for (String code : codes)
                System.out.println(code);
        }

    }

    void updateProcessing()
    {
        System.out.println("Updating processing...");
        List<MsProcessing> processingList = dataManager.loadAllEntities(MsProcessing.class);

        System.out.println("processing total count: "+processingList.size());

        for(MsProcessing processing : processingList)
        {
            MsStore materialsStore = getStore(processing.getMaterialsStoreId());
            MsStore productsStore = getStore(processing.getProductsStoreId());

            boolean msc = !checkStore(materialsStore);
            boolean psc = !checkStore(productsStore);

            if (msc || psc)
            {
                if(msc)
                    processing.setMaterialsStoreId(trueStore.getId());

                if(psc)
                    processing.setProductsStoreId(trueStore.getId());

                dataManager.updateEntity(processing);
                codes.add("processing: "+processing.getName());
            }
        }
    }

    void updateProcessingOrders()
    {
        System.out.println("Updating processing orders...");
        List<MsProcessingOrder> processingOrders = dataManager.loadAllEntities(MsProcessingOrder.class);

        System.out.println("processing orders total count: "+processingOrders.size());

        for(MsProcessingOrder processingOrder : processingOrders)
        {
            MsStore store = getStore(processingOrder.getStoreId());
            if (!checkStore(store))
            {
                processingOrder.setStoreId(trueStore.getId());
                dataManager.updateEntity(processingOrder);
                codes.add("processing order: "+processingOrder.getName());
            }
        }
    }

    void updateDemands()
    {
        System.out.println("Updating demands...");
        List<MsDemand> demands = dataManager.loadAllEntities(MsDemand.class);

        System.out.println("demands total count: "+demands.size());

        for(MsDemand demand : demands)
        {
            MsStore store = getStore(demand.getStoreId());
            if (!checkStore(store))
            {
                demand.setStoreId(trueStore.getId());
                dataManager.updateEntity(demand);
                codes.add("demand: "+demand.getName());
            }
        }
    }

    void updateSupplies()
    {
        System.out.println("Updating supplies...");
        List<MsSupply> supplies = dataManager.loadAllEntities(MsSupply.class);

        System.out.println("supplies total count: "+supplies.size());

        for(MsSupply s : supplies)
        {
            MsStore store = getStore(s.getStoreId());
            if (!checkStore(store))
            {
                s.setStoreId(trueStore.getId());
                dataManager.updateEntity(s);
                codes.add("supply: "+s.getName());
            }
        }
    }

    void updateCustomerOrders()
    {
        System.out.println("Updating customer orders...");
        List<MsCustomerOrder> customerOrders = dataManager.loadAllEntities(MsCustomerOrder.class);

        System.out.println("customer orders total count: "+customerOrders.size());

        for(MsCustomerOrder co : customerOrders)
        {
            MsStore store = getStore(co.getStoreId());
            if (!checkStore(store))
            {
                co.setStoreId(trueStore.getId());
                dataManager.updateEntity(co);
                codes.add("customerOrder: "+co.getName());
            }
        }
    }

    void updatePurchaseOrders()
    {
        System.out.println("Updating purchase orders...");
        List<MsPurchaseOrder> purchaseOrders = dataManager.loadAllEntities(MsPurchaseOrder.class);

        System.out.println("purchase orders total count: "+purchaseOrders.size());

        for(MsPurchaseOrder po : purchaseOrders)
        {
            MsStore store = getStore(po.getStoreId());
            if (!checkStore(store))
            {
                po.setStoreId(trueStore.getId());
                dataManager.updateEntity(po);
                codes.add("purchaseOrder: "+po.getName());
            }
        }
    }

}
