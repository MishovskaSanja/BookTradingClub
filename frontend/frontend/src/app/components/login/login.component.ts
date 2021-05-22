import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { TokenStorageService } from 'src/app/service/token-storage.service';
import { UserService } from '../../service/user.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private tokenStorage: TokenStorageService, private authService: AuthService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {}

  onSubmit(data){
      this.authService.login(data).subscribe(result => {
        this.tokenStorage.saveUser(result.username)
        this.tokenStorage.saveToken(result.accessToken)
        this.router.navigateByUrl('/books')
      })
  }


}
