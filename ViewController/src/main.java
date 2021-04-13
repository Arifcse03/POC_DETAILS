import javax.faces.event.ActionEvent;

import model.services.AppModuleImpl;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.ViewObject;

public class main {
    private RichTable pocTable;

    public main() {
    }
    public AppModuleImpl getAppModuleImpl() {
            DCBindingContainer bindingContainer =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            //BindingContext bindingContext = BindingContext.getCurrent();
            DCDataControl dc =
                bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
            AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
            return appM;
        }
        AppModuleImpl am =  getAppModuleImpl();
    public void action_call(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject searchVO=am.getPOCHeaderView1();
                ViewObject pocvo=am.getpocSearchVo1();
                String poc_id=null;
                int Buyer=0;
                int org=0;
                String orgname=null;
                try{
                    poc_id=pocvo.getCurrentRow().getAttribute("PocId").toString();
                }
                catch(Exception e) {
                    poc_id=null;
                }
        
        int pram=1;
              am.getDBTransaction().commit();
             /***ViewObject oder=am.getOrderRecapSummary1();
             ViewObject oder=am.getorder_recap_new_view1();
               oder.setNamedWhereClauseParam("param",pram);
               oder.setWhereClause("SEASON = '"+season+"' AND BUYER_ID = '"+Buyer+"' AND ORG_ID= '"+org+"'");
              **/
              
             
               searchVO.setNamedWhereClauseParam("param",pram);
               searchVO.setWhereClause("POC_ID = '"+poc_id+"'");
              
              
               
               searchVO.executeQuery();
               
            
             AdfFacesContext.getCurrentInstance().addPartialTarget(pocTable); 
              
              
        
        
        
    }

    public void setPocTable(RichTable pocTable) {
        this.pocTable = pocTable;
    }

    public RichTable getPocTable() {
        return pocTable;
    }
}
