import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './components/app/app.component';
import { BookComponent } from './components/book/book.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from '../app/components/login/login.component';
import { RegisterComponent } from '../app/components/register/register.component';
import { AddBookComponent } from '../app/components/add-book/add-book.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './components/header/header.component';
import { UsersComponent } from './components/users/users.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { TradesComponent } from './components/trades/trades.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { MyBooksComponent } from './components/my-books/my-books.component';
import { EditBookComponent } from './components/edit-book/edit-book.component';
import { CreateRequestComponent } from './components/create-request/create-request.component';
import { RequestComponent } from './components/requests/request.component';
import { authInterceptorProviders } from './components/auth.interceptor';
import { MyRequestsComponent } from './components/my-requests/my-requests.component';
import { IncomingRequestsComponent } from './components/incoming-requests/incoming-requests.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LogoutComponent } from './components/logout/logout.component';


@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    LoginComponent,
    RegisterComponent,
    AddBookComponent,
    HeaderComponent,
    UsersComponent,
    UserProfileComponent,
    TradesComponent,
    EditProfileComponent,
    MyBooksComponent,
    EditBookComponent,
    CreateRequestComponent,
    RequestComponent,
    MyRequestsComponent,
    IncomingRequestsComponent,
    LogoutComponent,

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
