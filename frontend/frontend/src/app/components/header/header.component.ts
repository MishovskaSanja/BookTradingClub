import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User



  constructor(private tokenStorage: TokenStorageService, private router: Router) {

  }

  ngOnInit(){
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  logout(){
    this.tokenStorage.logout()
    this.router.navigateByUrl("/books")
  }



}
