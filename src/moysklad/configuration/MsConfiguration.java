package moysklad.configuration;

import moysklad.configuration.nodes.ChildNode;
import moysklad.configuration.nodes.EntityNode;
import moysklad.configuration.nodes.NodeEntry;
import moysklad.configuration.nodes.RootNode;
import moysklad.core.MsUser;
import moysklad.entities.*;
import moysklad.mapping.audit.MsAttributeMapper;
import moysklad.mapping.common.MsProductMapper;
import moysklad.mapping.common.MsStoreMapper;
import moysklad.mapping.production.*;
import moysklad.mapping.purchasing.MsPurchaseOrderMapper;
import moysklad.mapping.purchasing.MsPurchaseOrderPositionMapper;
import moysklad.mapping.purchasing.MsSupplyMapper;
import moysklad.mapping.purchasing.MsSupplyPositionMapper;


import java.util.ArrayList;
import java.util.List;

public class MsConfiguration
{
    private String serverApiUrl;
    private String accountId;
    private int defaultPageSize = 50;
    private List<MsUser> users = new ArrayList<>();
    private List<RootNode> entityNodes;

    public static MsConfiguration createConfiguration()
    {
        List<RootNode> rootNodes = new ArrayList<>();

        RootNode rnPurchaseOrders = new RootNode("purchaseorder", new MsPurchaseOrderMapper(), "https://online.moysklad.ru/api/remap/1.2/entity/purchaseorder");
        ChildNode cnPurchaseOrderItem = new ChildNode("purchaseorderposition",new MsPurchaseOrderPositionMapper());
        rnPurchaseOrders.addNode("positions",cnPurchaseOrderItem);
        rootNodes.add(rnPurchaseOrders);

        RootNode rnSupply = new RootNode("supply", new MsSupplyMapper(), "https://online.moysklad.ru/api/remap/1.2/entity/supply");
        ChildNode cnSupplyPosition = new ChildNode("supplyposition", new MsSupplyPositionMapper());
        rnSupply.addNode("positions", cnSupplyPosition);
        rootNodes.add(rnSupply);

        RootNode rnProcessing = new RootNode("processing", new MsProcessingMapper(),"https://online.moysklad.ru/api/remap/1.2/entity/processing");
        ChildNode cnProcessingMaterial = new ChildNode("processingpositionmaterial",new MsProcessingPositionMaterialMapper());
        rnProcessing.addNode("materials", cnProcessingMaterial);
        ChildNode cnProcessingResult = new ChildNode("processingpositionresult", new MsProcessingPositionResultMapper());
        rnProcessing.addNode("products", cnProcessingResult);
        rootNodes.add(rnProcessing);

        RootNode rnProcessingPlan = new RootNode("processingplan", new MsProcessingPlanMapper(), "https://online.moysklad.ru/api/remap/1.2/entity/processingplan");
        ChildNode cnProcessingPlanMaterial = new ChildNode("processingplanmaterial", new MsProcessingPlanMaterialMapper());
        rnProcessingPlan.addNode("materials", cnProcessingPlanMaterial);
        ChildNode cnProcessingPlanProduct = new ChildNode("processingplanresult", new MsProcessingPlanResultMapper());
        rnProcessingPlan.addNode("products", cnProcessingPlanProduct);

        rootNodes.add(new RootNode("product", new MsProductMapper(),"https://online.moysklad.ru/api/remap/1.2/entity/product"));
        rootNodes.add(new RootNode("attributemetadata", new MsAttributeMapper(), "https://online.moysklad.ru/api/remap/1.2/entity/product/metadata/attributes"));

        rootNodes.add(new RootNode("store", new MsStoreMapper(),"https://online.moysklad.ru/api/remap/1.2/entity/store"));
        /*

        List<EntityNode_old> nodes = new ArrayList<>();

        EntityNode_old nPurchaseOrder = new EntityNode_old(new MsPurchaseOrderMapper());
        nodes.add(nPurchaseOrder);

        EntityNode_old nPurchaseOrderItem = new EntityNode_old(new MsPurchaseOrderPositionMapper());
        nPurchaseOrder.addChildNode("positions", nPurchaseOrderItem);

        EntityNode_old nCustomerOrder = new EntityNode_old(new MsCustomerOrderMapper());
        nodes.add(nCustomerOrder);

        EntityNode_old nSupply = new EntityNode_old(new MsSupplyMapper());
        nodes.add(nSupply);

        EntityNode_old nDemand = new EntityNode_old(new MsDemandMapper());
        nodes.add(nDemand);

        EntityNode_old nProcessingOrder = new EntityNode_old(new MsProcessingOrderMapper());
        nodes.add(nProcessingOrder);

        EntityNode_old nProcessing = new EntityNode_old(new MsProcessingMapper());
        nodes.add(nProcessing);

        EntityNode_old nProcessingPositionResult = new EntityNode_old(new MsProcessingPositionResultMapper());
        nProcessing.addChildNode("products",nProcessingPositionResult);


        EntityNode_old nProcessingPlan = new EntityNode_old(new MsProcessingPlanMapper());
        nodes.add(nProcessingPlan);

        EntityNode_old nStore = new EntityNode_old(new MsStoreMapper());
        nodes.add(nStore);

        EntityNode_old nCounterparty = new EntityNode_old(new MsCounterpartyMapper());
        nodes.add(nCounterparty);

        EntityNode_old nOrganization = new EntityNode_old(new MsOrganizationMapper());
        nodes.add(nOrganization);

        EntityNode_old nEmployee = new EntityNode_old(new MsEmployeeMapper());
        nodes.add(nEmployee);

        EntityNode_old nAudit = new EntityNode_old(new MsAuditMapper());
        nodes.add(nAudit);

        EntityNode_old nProduct = new EntityNode_old(new MsProductMapper());
        nodes.add(nProduct);
*/


        MsConfiguration result = new MsConfiguration();
        result.setAccountId("13a89ca6-38ea-11e9-9109-f8fc00011c68");
        result.setServerApiUrl("https://online.moysklad.ru/api/remap/1.2");

        result.getUsers().add(new MsUser("kea@ledmaster_pro","ledmaster@A00991994"));
        result.getUsers().add(new MsUser("admin@ledmaster_pro", "ledmaster_77"));//""ledmaster@A571"));

        //result.getUsers().add(new MsUser("json_1@ledmaster_pro","ledmaster"));
        //result.getUsers().add(new MsUser("json_2@ledmaster_pro", "ledmaster"));

        result.entityNodes = rootNodes;
        return result;
    }



    private EntityNode findNode(EntityNode parent, Class<? extends MsEntity> entityClass)
    {
        if(parent == null)
        {
            List<? extends EntityNode> nodes = getEntityNodes();
            for(EntityNode n : nodes)
            {
                if(n.getMapper().getEntityClass().equals(entityClass))
                    return n;
                EntityNode result = findNode(n,entityClass);
                if(result!=null)
                    return result;
            }
        }
        else
        {
            if (parent.getNodeEntries() != null)
                for (NodeEntry ne : parent.getNodeEntries())
                {
                    EntityNode n = ne.getNode();
                    if (n.getMapper().getEntityClass().equals(entityClass))
                        return n;
                    EntityNode result = findNode(n, entityClass);
                    if (result != null)
                        return result;
                }
        }

        return null;
    }

    public EntityNode getNode(Class<? extends MsEntity> entityClass)
    {
        EntityNode node = findNode(null, entityClass);
        if(node==null)
            throw new RuntimeException("EntityNode not found for: "+entityClass);
        return node;
    }

    public String getMsClassName(Class<? extends MsEntity> entityClass)
    {
        EntityNode node = getNode(entityClass);
        return node.getMsType();
    }


    public String getServerApiUrl()
    {
        return serverApiUrl;
    }
    public void setServerApiUrl(String serverApiUrl)
    {
        this.serverApiUrl = serverApiUrl;
    }

    public String getAccountId()
    {
        return accountId;
    }
    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public int getDefaultPageSize()
    {
        return defaultPageSize;
    }
    public void setDefaultPageSize(int defaultPageSize)
    {
        this.defaultPageSize = defaultPageSize;
    }

    public List<RootNode> getEntityNodes()
    {
        return entityNodes;
    }

    public List<MsUser> getUsers()
    {
        return users;
    }
}
