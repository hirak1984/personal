terraform {

  backend "s3" {
    bucket = "hrk-terraform-state"
    key = "personal/stage"
    region = "us-east-1"

    dynamodb_table = "terraform-state-lock-table"
  }
  required_providers {
    aws = {
      region = "us-east-2"
    }
  }
}
