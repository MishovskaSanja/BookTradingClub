import { User } from "./user";

export interface Book {
  id: number
  name: string
  description: string
  imgUrl: string
  owner: User
  status : String
}
