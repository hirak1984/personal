terraform {

  backend "s3" {
    bucket = "hrk-terraform-state"
    key = "personal/stage"
    region = "us-east-1"
    encrypt = true
    dynamodb_table = "terraform-state-lock-table"
  }
}
