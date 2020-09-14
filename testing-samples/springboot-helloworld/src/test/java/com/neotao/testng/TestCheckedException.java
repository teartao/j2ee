package com.neotao.testng;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TestCheckedException {

    OrderBo orderBo;
    Order data;


    @BeforeTest
    void setup() {
        orderBo = new OrderBo();

        data = new Order();
        data.setId(1000);
        data.setCreatedBy("maxsu");
    }

    @Test(expectedExceptions = OrderSaveException.class)
    public void throwIfOrderIsNull() throws OrderSaveException {
        orderBo.save(null);
    }

    @Test(timeOut = 5000)
    public void testThisShouldPass() throws InterruptedException {
        Thread.sleep(4000);
    }

    /*
     * Example : Multiple expected exceptions Test is success if either of the
     * exception is thrown
     */
    @Test(expectedExceptions = { OrderUpdateException.class, OrderNotFoundException.class })
    public void throwIfOrderIsNotExists() throws OrderUpdateException, OrderNotFoundException {
        orderBo.update(data);
    }

}


