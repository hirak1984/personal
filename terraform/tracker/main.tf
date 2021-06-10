resource "aws_dynamodb_table" "tracking-records" {
  name = "tracking-records"
  billing_mode = var.tracker_ddb_settings.billing_mode
  read_capacity = var.tracker_ddb_settings.read_capacity
  write_capacity = var.tracker_ddb_settings.write_capacity
  hash_key = "hashKey"
  range_key = "name"
  attribute {
    name = "hashKey"
    type = "N"
  }
  attribute {
    name = "name"
    type = "S"
  }

  tags = {
    Name = "tracking-records"
    Environment = var.env
  }
}
resource "aws_dynamodb_table" "tracking-suspense" {
  name = "tracking-suspense"
  billing_mode = var.tracker_ddb_settings.billing_mode
  read_capacity = var.tracker_ddb_settings.read_capacity
  write_capacity = var.tracker_ddb_settings.write_capacity
  hash_key = "hashKey"

  attribute {
    name = "hashKey"
    type = "N"
  }

  tags = {
    Name = "tracking-suspense"
    Environment = var.env
  }
}