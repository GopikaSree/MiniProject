
package testpkg;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepkg.base_class;
import pagepkg.CartPage;
import pagepkg.login_page;
import pagepkg.chkout_page;

public class chkout_test extends base_class {

    @Test
    public void chkoutTest() {

        // Step 1: Login
        login_page login = new login_page(driver);

        login.start("standard_user", "secret_sauce");
        login.login();

        // Step 2: Add products and open cart
        CartPage ob = new CartPage(driver);

        ob.AddtoCart();

        // Step 3: Initialise checkout page
        chkout_page cp = new chkout_page(driver);

        // Step 4: Checkout
        cp.checkout();

        cp.EnterChkoutDetails(
            "Gopika", "Menon", "695003"
        );

        cp.continueclick();

        cp.finish();

        // Step 5: Verify order completion
        Assert.assertEquals(
            cp.getCompleteMessage(),
            "Thank you for your order!"
        );
    }
}
