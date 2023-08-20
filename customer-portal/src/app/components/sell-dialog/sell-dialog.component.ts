import {Component, Inject, EventEmitter} from '@angular/core';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule} from '@angular/material/dialog';
import {NgIf} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { HttpClient } from '@angular/common/http';
import { DialogComponent } from '../buy-dialog/buy-dialog.component';


@Component({
  selector: 'app-sell-dialog',
  templateUrl: './sell-dialog.component.html',
  styleUrls: ['./sell-dialog.component.css'],
})
export class SellDialogComponent {

  "stockId": String;
  "bondId":String;
  "sellAmount": Number;
  "sellPrice": Number
  refreshAssets: EventEmitter<void> = new EventEmitter<void>();

  constructor(private http: HttpClient, @Inject(MAT_DIALOG_DATA) public data : any, private dialog: MatDialog) {}

  sellproduction = this.data.productionId;
  sellUrl :any;
  payload :any;

  onSubmit() {
    // 调用后端API并传递参数
    if(this.data.productionTypeName === "Stock"){
      this.sellUrl = 'http://localhost:8094/api/transactionRecord/sellStock';
      this.payload = {
      stockId: this.data.productionId,
      sellAmount: this.sellAmount,
      sellPrice: this.sellPrice
    };
     console.log('go',this.payload)
  }
  else if(this.data.productionTypeName === "Bond"){
      this.sellUrl = 'http://localhost:8094/api/transactionRecord/sellBond';
      this.payload = {
      bondId: this.data.productionId,
      sellAmount: this.sellAmount,
      sellPrice: this.sellPrice
    };
    console.log(this.payload)
  } 

    this.http.post(this.sellUrl, this.payload).subscribe(
      (response: any)=> {
        // 处理API响应
        if (response.code === 200) {
          this.openDialog('Sussceed', response.msg);
        } else if (response.code === 404) {
          this.openDialog('Failed', response.msg);
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

// @Component({
//   selector: 'sell-dialog-component',
//   template: `
//     <h2 mat-dialog-title>{{ data.title }}</h2>
//     <mat-dialog-content>{{ data.message }}</mat-dialog-content>
//     <mat-dialog-actions>
//       <button mat-button mat-dialog-close (click)="onClose()">close</button>
//     </mat-dialog-actions>
//   `
// })
// export class sellDialogComponent {
//   constructor(@Inject(MAT_DIALOG_DATA) public data: { title: string, message: string }) {}
//   onClose(): void {
//     window.location.reload();
//   }
// }
