package de.janosch.springbootmongo;

import de.janosch.springbootmongo.repository.AddressRepository;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * AddressRESTTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressRESTTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressRepository addressRepository;

    private ObjectId id;

    @After
    public void doAfter() {
        addressRepository.deleteAll();
    }

    @Test
    public void postV1AddressTest() {
        Address address = getMusterAddress();
        ResponseEntity<Address> responseEntity = this.restTemplate.postForEntity("/v1/address", address, Address.class);

        Address addressResponse = responseEntity.getBody();
        assertThat(addressResponse, notNullValue());
        assertThat(addressResponse.getId(), notNullValue());
        id = new ObjectId(addressResponse.getId());
        assertThat(addressResponse.getCity(), is(address.getCity()));
        assertThat(addressResponse.getFirstname(), is(address.getFirstname()));
        assertThat(addressResponse.getLastname(), is(address.getLastname()));
        assertThat(addressResponse.getPostcode(), is(address.getPostcode()));
        assertThat(addressResponse.getStreet(), is(address.getStreet()));
    }

    @Test
    public void getV1AddressTest() {
        postV1AddressTest();
        postV1AddressTest();
        postV1AddressTest();

        Address[] response = restTemplate.getForObject("/v1/address", Address[].class);
        List<Address> addresses = Arrays.asList(response);

        assertThat(addresses, notNullValue());
        assertThat(addresses.size(), is(3));
    }

    @Test
    public void getV1AddressByAddressIdTest() {
        postV1AddressTest();

        Address addressResponse = restTemplate.getForObject("/v1/address/"+id, Address.class);

        Address address = getMusterAddress();
        assertThat(addressResponse, notNullValue());
        assertThat(addressResponse.getId(), notNullValue());
        assertThat(addressResponse.getCity(), is(address.getCity()));
        assertThat(addressResponse.getFirstname(), is(address.getFirstname()));
        assertThat(addressResponse.getLastname(), is(address.getLastname()));
        assertThat(addressResponse.getPostcode(), is(address.getPostcode()));
        assertThat(addressResponse.getStreet(), is(address.getStreet()));
    }

    @Test
    public void putV1AddressByAddressIdTest() {
        Address address = getMusterAddress();

        String id = new ObjectId().toHexString();
        HttpEntity<Address> requestUpdate = new HttpEntity<>(address);
        ResponseEntity<Void> response = restTemplate.exchange("/v1/address/"+id, HttpMethod.PUT, requestUpdate, Void.class);

        assertThat(response.getStatusCode().value(), is(404));

        postV1AddressTest();
        requestUpdate = new HttpEntity<>(address.withFirstname("Max2"));
        response = restTemplate.exchange("/v1/address/"+this.id.toHexString(), HttpMethod.PUT, requestUpdate, Void.class);

        assertThat(response.getStatusCode().value(), is(200));

        Address addressResponse = restTemplate.getForObject("/v1/address/"+this.id.toHexString(), Address.class);
        assertThat(addressResponse.getFirstname(), is("Max2"));
    }

    private Address getMusterAddress() {
        return new Address()
                .withCity("Mustercity")
                .withFirstname("Max")
                .withLastname("Mustermann")
                .withPostcode("9999")
                .withStreet("Musterstrasse");
    }
}