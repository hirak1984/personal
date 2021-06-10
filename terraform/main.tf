provider "aws"{
  region = "us-east-1"
}

module "tracker" {
  source = "./tracker"
  tracker_ddb_settings = {
    billing_mode = "PROVISIONED"
    read_capacity = 10
    write_capacity = 10
  }
  env="prod"
}