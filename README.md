# MoySkladApi
Java api for moysklad.ru service

Api allows you to upload data from the MySklad service and work with them in the form of relational data, in particular, build reports using jxls

use case:

        MsConfiguration cfg = MsConfiguration.createConfiguration();
        cfg.setAccountId("xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx");
        cfg.getUsers().add(new MsUser("xxx@xxx","xxx"));
        cfg.getUsers().add(new MsUser("yyy@xxx", "yyy"));

        MsDataSet ds = new MsDataSet();
        MsDataLoader loader = new MsDataLoader(cfg, ds);
        MsAsyncLoadManager alm = new MsAsyncLoadManager(loader);
        alm.load();
        
        for(MsDemand demand : ds.getEntities(MsDemand.class)) {
            System.out.println(demand.getName());
        }
