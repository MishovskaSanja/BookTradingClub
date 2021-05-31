import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { TokenStorageService } from 'src/app/service/token-storage.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: User

  constructor(private tokenStorage: TokenStorageService, private router: Router) {
  }

  ngOnInit() {
    this.user = JSON.parse(sessionStorage.getItem("user")) as User
  }

  logout() {
    this.tokenStorage.logout()
   if(this.router.url == '/books'){
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
        this.router.navigate([currentUrl]);
    });
   }
   else{
    this.router.navigateByUrl("/books")

   }
  }
}
