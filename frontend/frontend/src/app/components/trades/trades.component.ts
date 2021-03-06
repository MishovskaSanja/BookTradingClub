import { Component, OnInit } from '@angular/core';
import { Request } from 'src/app/model/request'
import { RequestService } from 'src/app/service/request.service';

@Component({
  selector: 'app-trades',
  templateUrl: './trades.component.html',
  styleUrls: ['./trades.component.css']
})
export class TradesComponent implements OnInit {

  trades: Request[];

  constructor(public requestService: RequestService) { }

  ngOnInit(): void {
    this.getAllTrades()
  }

  getAllTrades() {
    this.requestService.getAllTrades().subscribe(result => {
      this.trades = result;
    });
  }
}
