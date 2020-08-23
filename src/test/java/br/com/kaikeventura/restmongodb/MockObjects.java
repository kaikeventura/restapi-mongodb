package br.com.kaikeventura.restmongodb;

import br.com.kaikeventura.restmongodb.model.Address;
import br.com.kaikeventura.restmongodb.model.User;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;

import java.text.SimpleDateFormat;

public class MockObjects {

    public static void objects() {
        Fixture.of(User.class).addTemplate("uniqueUser", new Rule(){{
            add("id", "5f4179f801d0360b0420c944");
            add("createdDate", afterDate(
                    "2020-08-22T20:25:33.939-0300",
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
            );
            add("lastModifiedDate", afterDate(
                    "2020-08-22T20:25:33.939-0300",
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
            );
            add("name", "Maicon");
            add("lastName", "Douglas");
            add("age", "25");
            add("documentNumber", "68010794090");
            add("address", one(Address.class, "uniqueAddress"));
        }});

        Fixture.of(Address.class).addTemplate("uniqueAddress", new Rule(){{
            add("address", "Rua Jujuba das Pegadas, 234");
            add("postalCode", "16900016");
            add("city", "Suzano");
            add("state", "São Paulo");
        }});

        Fixture.of(User.class).addTemplate("manyUsers", new Rule(){{
            add("id", uniqueRandom(
                    "5f4179f801d0360b0420c944",
                    "9l4179f801d03646g420c944",
                    "689f79f801d0360b0420c944",
                    "3f2e79f801d0360b0420c944"
            ));
            add("createdDate", afterDate(
                    "2020-08-22T20:25:33.939-0300",
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
            );
            add("lastModifiedDate", afterDate(
                    "2020-08-22T20:25:33.939-0300",
                    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"))
            );
            add("name", firstName());
            add("lastName", lastName());
            add("age", random(Integer.class, range(1, 100)));
            add("documentNumber", uniqueRandom(
                    "68010794090",
                    "71809376068",
                    "05555144040",
                    "40015422003"
            ));
            add("address", one(Address.class, "uniqueAddress"));
        }});

        Fixture.of(Address.class).addTemplate("manyAddresses", new Rule(){{
            add("address", uniqueRandom(
                    "Rua Jujuba das Pegadas, 234",
                    "Rua Niterói, 46",
                    "Praça Monsenhor Albino",
                    "Rua 9"
            ));
            add("postalCode", uniqueRandom(
                    "16900-016",
                    "69028408",
                    "77062086",
                    "89053170"
            ));
            add("city", uniqueRandom(
                    "Suzano",
                    "Blumenau",
                    "Araguaína",
                    "Serra"
            ));
            add("state", uniqueRandom(
                    "São Paulo",
                    "Bahia",
                    "Rio de Janeiro",
                    "Acre"
            ));
        }});
    }
}
