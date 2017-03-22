package com.ifarma.ifarma.test;

import com.ifarma.ifarma.activities.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testFiltraPorPreco_e_Pesq extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public testFiltraPorPreco_e_Pesq() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.ifarma.ifarma.activities.MainActivity'
		solo.waitForActivity(com.ifarma.ifarma.activities.MainActivity.class, 2000);
        //Set default small timeout to 138879 milliseconds
		Timeout.setSmallTimeout(138879);
        //Click on ImageView
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.filter_btn));
        //Click on Ordenação por preço
		solo.clickOnView(solo.getView(android.R.id.text1));
        //Click on Farmacia
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.pharmaButton));
        //Click on Remedio
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.medicineButton));
        //Assert that: 'NavigationTabBar' is shown
		assertTrue("'NavigationTabBar' is not shown!", solo.waitForView(solo.getView(com.ifarma.ifarma.R.id.ntb_horizontal)));
        //Assert that: 'NavigationTabBar' is shown
		assertTrue("'NavigationTabBar' is not shown!", solo.waitForView(solo.getView(com.ifarma.ifarma.R.id.ntb_horizontal)));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.txtsearch));
        //Enter the text: 'Produto 4'
		solo.clearEditText((android.widget.EditText) solo.getView(com.ifarma.ifarma.R.id.txtsearch));
		solo.enterText((android.widget.EditText) solo.getView(com.ifarma.ifarma.R.id.txtsearch), "Produto 4");
        //Click on Produto 4
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.txtsearch));
        //Click on Produto 4 R$ 5.00 teste Farmacia Dias
		solo.clickInRecyclerView(0, 0);
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
	}
}
