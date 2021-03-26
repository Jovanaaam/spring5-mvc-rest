package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CustomerMapperTest{

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception{
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        Assert.assertEquals(FIRSTNAME, customerDTO.getFirstName());
        Assert.assertEquals(LASTNAME, customerDTO.getLastName());
    }

}