variable "tracker_ddb__settings" {
  type = object({
    billing_mode = String
    read_capacity = Number
    write_capacity = Number
  })
  default = {
    billing_mode = "PROVISIONED"
    read_capacity = 20
    write_capacity = 20
  }
}
variable "env" {
  type: string
  default: "prod"
}