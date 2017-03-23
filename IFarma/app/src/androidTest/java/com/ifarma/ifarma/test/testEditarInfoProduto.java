package com.ifarma.ifarma.test;

import com.ifarma.ifarma.activities.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class testEditarInfoProduto extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public testEditarInfoProduto() {
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
        //Wait for activity: 'com.ifarma.ifarma.activities.MainActivity'
		assertTrue("com.ifarma.ifarma.activities.MainActivity is not found!", solo.waitForActivity(com.ifarma.ifarma.activities.MainActivity.class));
        //Set default small timeout to 138879 milliseconds
		Timeout.setSmallTimeout(138879);
        //Click on Produto 1 R$ 14.00 bom demais Farmacia Dias
		solo.clickInRecyclerView(0, 0);
        //Click on Editar
		solo.clickOnView(solo.getView(android.R.id.button3));
        //Click on Produto 1
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.input_edit_name_product));
        //Enter the text: 'Pomada Pra Curar Chifre'
		solo.clearEditText((android.widget.EditText) solo.getView(com.ifarma.ifarma.R.id.input_edit_name_product));
		solo.enterText((android.widget.EditText) solo.getView(com.ifarma.ifarma.R.id.input_edit_name_product), "Pomada Pra Curar Chifre");
        //Click on LSD
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.input_edit_lab_product));
        //Enter the text: 'CASA DAS PRIMAS'
		solo.clearEditText((android.widget.EditText) solo.getView(com.ifarma.ifarma.R.id.input_edit_lab_product));
		solo.enterText((android.widget.EditText) solo.getView(com.ifarma.ifarma.R.id.input_edit_lab_product), "CASA DAS PRIMAS");
        //Click on Salvar
		solo.clickOnView(solo.getView(com.ifarma.ifarma.R.id.btn_edit));
        //Click on Pomada Pra Curar Chifre R$ 14.00 bom demais Farmacia Dias
		solo.clickInRecyclerView(1, 0);
        //Click on OK
		solo.clickOnView(solo.getView(android.R.id.button1));
	}
}
