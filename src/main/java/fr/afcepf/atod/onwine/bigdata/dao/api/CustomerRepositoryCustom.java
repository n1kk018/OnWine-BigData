package fr.afcepf.atod.onwine.bigdata.dao.api;

import fr.afcepf.atod.onwine.bigdata.domain.Customer;

public interface CustomerRepositoryCustom {
    Customer getRandomCustomer(int paramNb_order);
}
