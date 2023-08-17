import { AllHoldAssets } from './../../model/AllHoldAssets';
import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import {DataSource} from '@angular/cdk/collections';
import {CdkTableModule} from '@angular/cdk/table';
import {BehaviorSubject, Observable, subscribeOn} from 'rxjs';
import {AllholdassetsService} from 'src/app/services/allholdassets.service';
import { MatTableDataSource } from '@angular/material/table';




@Component({
  selector: 'app-all-hold-assets',
  templateUrl: './all-hold-assets.component.html',
  styleUrls: ['./all-hold-assets.component.css'],
})
export class AllHoldAssetsComponent {

  title = 'matTableFromApi';
  listAllHoldAssets!: AllHoldAssets[];

  constructor(private dataService: AllholdassetsService) { }

  ngOnInit() {
    this.fetchAssets()
  }
dataSource: any;

fetchAssets(){
  this.dataService.getAllAssetsValue().subscribe(res=>{
    this.listAllHoldAssets = res.data
    this.dataSource = new MatTableDataSource(this.listAllHoldAssets)
    console.log('list of AllHoldAssets', this.listAllHoldAssets)
    console.log('dataS', this.dataSource)
  })
}
  displayedColumns: string[] = ['holdAssetsId', 'userId', 'productionId', 'productionTypeName','productName','productionAmount','holdingCost','income','incomeRate'];
}



