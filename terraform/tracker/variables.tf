variable "tracker_ddb_settings" {
  type = object({
    billing_mode = string
    read_capacity = number
    write_capacity = number
  })
  default = {
    billing_mode = "PROVISIONED"
    read_capacity = 20
    write_capacity = 20
  }
}
variable "env" {
  type= string
  default= "prod"
}