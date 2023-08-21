import { Component, Inject, EventEmitter, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-buy-dialog',
  templateUrl: './buy-dialog.component.html',
  styleUrls: ['./buy-dialog.component.css'],
})


export class BuyDialogComponent {

    "stockId": String;
    "buyAmount": Number;
    "bondId": String;
    refreshAssets: EventEmitter<void> = new EventEmitter<void>();

    
    constructor(private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data : any, private dialog: MatDialog,
      ) {}

    ngOnInit() {
      console.log("data", this.buyproduction);
      
    }
    buyproduction = this.data.productionId;
    buyUrl :any;
    payload :any;

    onSubmit() {

      if(this.data.productionTypeName === "Stock"){
          this.buyUrl = 'http://localhost:8094/api/transactionRecord/buyStock';
          this.payload = {
          stockId: this.data.productionId,
          buyAmount: this.buyAmount
        };
         console.log('go',this.payload)
      }
      else if(this.data.productionTypeName === "Bond"){
          this.buyUrl = 'http://localhost:8094/api/transactionRecord/buyBond';
          this.payload = {
          bondId: this.data.productionId,
          buyAmount: this.buyAmount
        };
      }  

      this.http.post(this.buyUrl, this.payload).subscribe(
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


@Component({
  selector: 'dialog-component',
  template: `
    <h2 mat-dialog-title>{{ data.title }}</h2>
    <mat-dialog-content>{{ data.message }}</mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button mat-dialog-close (click)="onClose()">close</button>
    </mat-dialog-actions>
  `
})
export class DialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: { title: string, message: string }) {}
  onClose(): void {
    window.location.reload();
  }
}