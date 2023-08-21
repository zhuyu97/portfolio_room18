import { Component, Inject, EventEmitter, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { BuyDialogComponent, DialogComponent } from '../buy-dialog/buy-dialog.component';

@Component({
  selector: 'app-buy-stock-dialog',
  templateUrl: './buy-stock-dialog.component.html',
  styleUrls: ['./buy-stock-dialog.component.css']
})
export class BuyStockDialogComponent {

  "buyAmount": Number;

  refreshAssets: EventEmitter<void> = new EventEmitter<void>();
  
  constructor(private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data : any, private dialog: MatDialog,
    ) {}

  ngOnInit() {
    console.log("data", this.data);
    
  }
  stockId = this.data.stockId;
  buyUrl :any;
  payload :any;

  onSubmit() {      
    const buyUrl = 'http://localhost:8094/api/transactionRecord/buyStock';
    const payload = {
    stockId: this.data.stockId,
    buyAmount: this.buyAmount
  }

    this.http.post(buyUrl, payload).subscribe(
      (response: any)=> {
        // 处理API响应
        if (response.code === 200) {
          this.openDialog('Successfully', response.msg);
        } else if (response.code === 404) {
          this.openDialog('Error while buying', response.msg);
        }
      },
      error => {
        // 处理错误
        console.error(error);
        this.openDialog('Failed', 'Input error');
      }
    );
  }

  openDialog(title: string, message: string): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: { title, message }
    });

  }

}
