package biz.eurosib.lkdkp.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ServiceConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("biz.eurosib.lkdkp.client.wsdl");
        return marshaller;
    }

    @Bean
    public ServiceClient countryClient(Jaxb2Marshaller marshaller) {
        ServiceClient client = new ServiceClient();
//        client.setDefaultUri("http://tsxdatacoretest/TSDatacoreTest2/ws/DataCoreWS_response");
        client.setDefaultUri("http://atg-ca.selfip.biz/samples2/Service1.svc");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
