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

resource "aws_instance" "app_server" {
  ami           = "ami-0a306845f8cfbd41a"
  instance_type = "t2.micro"

  tags = {
    Name = "SkedulerAppServerInstance"
  }
}
