import { HttpClient } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';
import { ChartModule } from 'primeng/chart'
import { HttpClientModule } from '@angular/common/http';
import { AllHoldAssetsComponent } from './components/all-hold-assets/all-hold-assets.component';
import { AllholdassetsService } from './services/allholdassets.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableModule } from '@angular/material/table';
import { Routes, RouterModule } from '@angular/router';
import { LineChartComponent } from './components/line-chart/line-chart.component';
import { StockComponent } from './components/stock/stock.component';
import { BuyDialogComponent, DialogComponent } from './components/buy-dialog/buy-dialog.component';
import { SellDialogComponent } from './components/sell-dialog/sell-dialog.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginator, MatPaginatorModule} from '@angular/material/paginator';
import { MatSort, MatSortModule} from '@angular/material/sort';
import { BuyStockDialogComponent } from './components/buy-stock-dialog/buy-stock-dialog.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { StockHistoryComponent } from './components/stock-history/stock-history.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PieChartComponent,
    AllHoldAssetsComponent,
    LineChartComponent,
    StockComponent,
    BuyDialogComponent,
    SellDialogComponent,
    DialogComponent,
    BuyStockDialogComponent,
    StockHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ChartModule,
    HttpClientModule,
    MatFormFieldModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    FormsModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatPaginatorModule,
    MatSortModule,
    MatToolbarModule
  ],
  providers: [AllholdassetsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
