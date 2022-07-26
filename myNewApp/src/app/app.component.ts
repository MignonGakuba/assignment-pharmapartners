import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Currency } from './currency.model';
import { JsonResult } from './json-result.model';
import { BackendAPIService } from './services/backend-api.service';

const COLUMNS_SCHEMA = [
  {
    key: 'TICKER',
    type: 'text',
    label: 'TICKER',
  },
  {
    key: 'Name',
    type: 'text',
    label: 'NAME',
  },
  {
    key: 'NUMBER_OF_COINS',
    type: 'text',
    label: 'NUMBER_OF_COINS',
  },
  {
    key: 'MARKET_CAP',
    type: 'text',
    label: 'MARKET_CAP',
  },
  {
    key: 'isEdit',
    type: 'isEdit',
    label: '',
  },
];
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})



export class AppComponent {

  title = 'Assignment Currencies WebApp';
  
  constructor(
    private currencyService: BackendAPIService)
    {}

    displayedColumns: string[] = COLUMNS_SCHEMA.map((col) => col.key);
    columnsSchema: any = COLUMNS_SCHEMA;
    dataSource =  new MatTableDataSource<Currency>();
    
    ngOnInit() {
     
      this.getAllCurreniesInformation();
    }

    getAllCurreniesInformation()
    {
      this.currencyService.getAllCurrency().subscribe((data:JsonResult) => {
     if(data.result) {
          
       // convert the ist of item to right class 
      let retreviedCurrencies: Object[] =  data.item as Object [];
      retreviedCurrencies.map(value =>{
      let currency : Currency = new Currency(value);
      //add to list 
      console.log(currency.Name);
      this.dataSource.data.unshift(currency);
      });
         console.log(data.message);
       } else {
         console.log(data.message);
       }
     },
     error => {
       console.log(error, 'error');
     });
    }


  //added new currency 
  addRow() {
    const newCurrency = {
      Id: 0 ,
      Name: '',
      TICKER: '',
      NUMBER_OF_COINS: '',
      MARKET_CAP: '',
    };
    this.dataSource.data = [newCurrency, ...this.dataSource.data];
  }


  //create and compile 
  removeRow(id: number) {
     this.dataSource.data = this.dataSource.data.filter((u) => u.Id !== id);     
  }

  confirmToUpdate(id: number){
   
    //filter fromt the list
    //create service methode to update the selected currency with new value
  }


}
  function addRow() {
    throw new Error('Function not implemented.');
  }

