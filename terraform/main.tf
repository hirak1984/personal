module "tracker" {
  source = "./tracker"
  dynamodb_tracker_settings = {
    billing_mode = "PROVISIONED"
    read_capacity = 10
    write_capacity = 10
  }
  env="prod"
}