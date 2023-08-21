import { AllHoldAssets } from './../../model/AllHoldAssets';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { AllholdassetsService } from 'src/app/services/allholdassets.service';
import { MatTableDataSource } from '@angular/material/table';
import { BuyDialogComponent } from '../buy-dialog/buy-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { SellDialogComponent } from '../sell-dialog/sell-dialog.component';



@Component({
  selector: 'app-all-hold-assets',
  templateUrl: './all-hold-assets.component.html',
  styleUrls: ['./all-hold-assets.component.css'],
})
export class AllHoldAssetsComponent {

  title = 'matTableFromApi';
  listAllHoldAssets!: AllHoldAssets[];

  constructor(private dataService: AllholdassetsService,private matDialog:MatDialog) { }

  ngOnInit() {
    this.fetchAssets()
  }
  dataSource: any;

  fetchAssets(){
    this.dataService.getAllAssetsValue().subscribe(res=>{
      this.listAllHoldAssets = res.data
      this.dataSource = new MatTableDataSource(this.listAllHoldAssets)
    })
  }
  displayedColumns: string[] = ['holdAssetsId', 'userId', 'productionId', 'productionTypeName','productName','productionAmount','holdingCost','income','incomeRate','action'];

  refreshAssets() {
    this.fetchAssets()
  }

  openBuyDialog(element : any) {
    this.matDialog.open(BuyDialogComponent,{
      width:'350px',
      data: element
    });
  }
  openSellDialog(element : any) {
    this.matDialog.open(SellDialogComponent,{
      width:'350px',
      data: element
    })
  }
}



