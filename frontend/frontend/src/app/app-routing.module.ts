import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookComponent } from './components/book/book.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { UsersComponent } from './components/users/users.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AddBookComponent } from './components/add-book/add-book.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { MyBooksComponent } from './components/my-books/my-books.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { RequestComponent } from './requests/request.component';
import { TradesComponent } from './components/trades/trades.component';


const routes: Routes = [
  { path: '', component: BookComponent},
  { path: 'books', component: BookComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: 'users', component: UsersComponent},
  { path: "addBook", component: AddBookComponent},
  { path: "editBook", component: EditBookComponent},
  { path: "user/edit-profile", component: EditProfileComponent},
  { path: 'user/myBooks', component: MyBooksComponent},
  { path: 'user/profile', component: UserProfileComponent},
  { path: 'requests', component: RequestComponent},
  { path: 'trades', component: TradesComponent},



];
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports : [RouterModule]
})
export class AppRoutingModule { }
