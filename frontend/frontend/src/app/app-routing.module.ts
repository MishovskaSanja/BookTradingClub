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


const routes: Routes = [
  { path: '', component: BookComponent},
  { path: 'books', component: BookComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: 'users', component: UsersComponent},
  { path: "addbook", component: AddBookComponent},
  { path: "user/edit-profile", component: EditProfileComponent},
  { path: 'user/myBooks', component: MyBooksComponent},
  { path: 'user/profile', component: UserProfileComponent},

];
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports : [RouterModule]
})
export class AppRoutingModule { }
