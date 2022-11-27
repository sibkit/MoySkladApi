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
import moysklad.mapping.products.MsMoveMapper;
import moysklad.mapping.products.MsMovePositionMapper;
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

    private static String getEntityPath(String entityName) {
        return "https://online.moysklad.ru/api/remap/1.2/entity/"+entityName;
    }


    public static MsConfiguration createConfiguration()
    {
        List<RootNode> rootNodes = new ArrayList<>();

        RootNode rnPurchaseOrders = new RootNode("purchaseorder", new MsPurchaseOrderMapper(), getEntityPath("purchaseorder"));
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
        rootNodes.add(rnProcessingPlan);

        rootNodes.add(new RootNode("product", new MsProductMapper(),"https://online.moysklad.ru/api/remap/1.2/entity/product"));
        rootNodes.add(new RootNode("attributemetadata", new MsAttributeMapper(), "https://online.moysklad.ru/api/remap/1.2/entity/product/metadata/attributes"));

        rootNodes.add(new RootNode("store", new MsStoreMapper(),"https://online.moysklad.ru/api/remap/1.2/entity/store"));

        RootNode rnMove = new RootNode("move", new MsMoveMapper(), "https://online.moysklad.ru/api/remap/1.2/entity/move");
        ChildNode cnMovePosition = new ChildNode("moveposition", new MsMovePositionMapper());
        rnMove.addNode("positions", cnMovePosition);
        rootNodes.add(rnMove);

        MsConfiguration result = new MsConfiguration();
        result.setServerApiUrl("https://online.moysklad.ru/api/remap/1.2");


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
