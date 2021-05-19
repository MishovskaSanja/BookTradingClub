import { User } from "./user";

export interface Book {
  id: bigint
  name: string
  description: string
  owner: User
  status : String
}
