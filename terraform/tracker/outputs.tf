output "tracking-records" {
  description = "ARN of the ddb table for tracking records"
  value       = aws_dynamodb_table.tracking-records.arn
}

output "tracking-suspense" {
  description = "ARN of the ddb table for tracking suspense"
  value       = aws_dynamodb_table.tracking-suspense.arn
}