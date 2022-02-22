package com.dcpear.main;
/**
 * Drools project for a jewellery shop to give discount to the type of jewellery.
 * Rules for the dicount calculations are written in the rules.xml file.
 *  Xml format, decision tables, is a very good option to use where rules are going
 *  to be changed frequently by non-programmers
 */

import com.dcpear.model.Product;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.Scanner;

public class DroolsTest {
    public static void main(String[] args) {
        try {
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            //Get the session named kseesion-rule that we defined in kmodule.xml above.
            //Also by default the session returned is always stateful.
            KieSession kSession = kContainer.newKieSession("ksession-rule");

            Product product = new Product();
            Scanner sc= new Scanner(System.in);
            System.out.print("Enter jewellery type - ");
            String str= sc.nextLine();
            product.setType(str);


            FactHandle fact1 = kSession.insert(product);

            kSession.insert(product);
            kSession.fireAllRules();

            System.out.println("The discount for the jewellery product "
                    + product.getType() + " is " + product.getDiscount());

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
