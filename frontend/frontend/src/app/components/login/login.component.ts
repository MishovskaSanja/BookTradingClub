import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../service/user.service'
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  constructor(private userService: UserService,private  router: Router) { }

  ngOnInit(): void {}

  onSubmit(data){
    this.userService.login(data).subscribe(result => {
      sessionStorage.setItem("user", JSON.stringify(result))
      const navigationDetails: string[] = ['/books'];
    this.router.navigate(navigationDetails);
    })


  }


}
