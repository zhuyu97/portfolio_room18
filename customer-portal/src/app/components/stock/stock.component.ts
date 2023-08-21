import { Component, OnInit, Inject } from '@angular/core';
import { AllStockService } from 'src/app/services/all-stock.service';
import { AfterViewInit, ViewChild} from '@angular/core';
import { MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { MatSort, MatSortModule} from '@angular/material/sort';
import { MatTableDataSource, MatTableModule} from '@angular/material/table';
import { BuyDialogComponent, DialogComponent } from '../buy-dialog/buy-dialog.component';
import { HttpClient } from '@angular/common/http';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { BuyStockDialogComponent } from '../buy-stock-dialog/buy-stock-dialog.component';
import {MatToolbarModule} from '@angular/material/toolbar';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit{
  displayedColumns: string[] = ['stockId', 'stockName', 'stockPrice', 'time', 'action'];
  dataSource!: MatTableDataSource<any>;
  "stockId": String;
  "buyAmount": Number;
 

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private allStock : AllStockService, private http: HttpClient,  private dialog: MatDialog,
    private matDialog:MatDialog) {
  
  }

  ngOnInit(): void {
      this.getAllStocks();
  }

  getAllStocks() {
    this.allStock.getAllStock().subscribe({
      next:(res) => {
        console.log(res.data);
        this.dataSource = new MatTableDataSource(res.data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort
      },
      error:(err)=>{
        alert("Error while fetching the Records!!")
      }
    })
  }

  openBuyStockDialog(element : any) {
    this.matDialog.open(BuyStockDialogComponent,{
      width:'350px',
      data: element
    });
  }

  applyFilter(event: Event) {
      const filterValue = (event.target as HTMLInputElement).value;
      this.dataSource.filter = filterValue.trim().toLowerCase();

      if (this.dataSource.paginator) {
        this.dataSource.paginator.firstPage();
      }
    }
}