terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region  = "ap-northeast-2"
}

# resource "<provider>_resourceType" "name" {
#     config options ...
#     key1 = "value"
#     key2 = "another value"
# }

resource "aws_vpc" "skeduler-vpc" {
  cidr_block = "10.0.0.0/16"
}

resource "aws_subnet" "subnet-1" {
  vpc_id     = aws_vpc.skeduler-vpc.id
  cidr_block = "10.0.1.0/24"

  tags = {
    Name = "public-subnet1"
  }
}

resource "aws_instance" "skeduler-app-server" {
  ami           = "ami-0a306845f8cfbd41a"
  instance_type = "t2.micro"

  tags = {
    Name = "SkedulerAppServerInstance"
  }
}

resource "aws_db_instance" "skeduler-db" {
    allocated_storage   = 10
    db_name             = "skeduler"
    engine              = "mysql"
    engine_version      = "5.7"
    instance_class      = "db.t3.micro"
    username            = "foo"
    password            = "foobarbaz"
    skip_final_snapshot = true
}
