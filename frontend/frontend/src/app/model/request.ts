import { Book } from "./book";
import { User } from "./user";

export interface Request {
  requestId: bigint
  userRequesting: User
  userReceiving: User
  wantedBook: Book
  bookToGive: Book
}
