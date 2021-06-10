resource "aws_dynamodb_table" "tracking-records" {
  name = "tracking-records"
  billing_mode = var.tracker_ddb__settings.billing_mode
  read_capacity = var.tracker_ddb__settings.read_capacity
  write_capacity = var.tracker_ddb__settings.write_capacity
  hash_key = "hashKey"

  attribute {
    name = "hashKey"
    type = "N"
  }
  attribute {
    name = "name"
    type = "S"
  }

  attribute {
    name = "recordCategory"
    type = "S"
  }
  attribute {
    name = "eventDate"
    type = "S"
  }
  attribute {
    name = "duration"
    type = "N"
  }
  attribute {
    name = "durationUnit"
    type = "S"
  }
  attribute {
    name = "amount"
    type = "N"
  }
  attribute {
    name = "amountUnit"
    type = "S"
  }
  attribute {
    name = "remarks"
    type = "S"
  }

  tags = {
    Name = "tracking-records"
    Environment = var.env
  }
}
resource "aws_dynamodb_table" "tracking-suspense" {
  name = "tracking-suspense"
  billing_mode = var.tracker_ddb__settings.billing_mode
  read_capacity = var.tracker_ddb__settings.read_capacity
  write_capacity = var.tracker_ddb__settings.write_capacity
  hash_key = "hashKey"

  attribute {
    name = "hashKey"
    type = "N"
  }
  attribute {
    name = "rawRequest"
    type = "S"
  }
  attribute {
    name = "eventDate"
    type = "S"
  }
  tags = {
    Name = "tracking-suspense"
    Environment = var.env
  }
}