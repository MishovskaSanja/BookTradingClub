import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from '../../service/user.service'

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService, private authService: AuthService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    this.tokenStorage.logout
    this.router.navigateByUrl('/books')
  }



}
