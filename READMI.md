### Command to check aws profile using terminal
1. To list the number of  profiles 
```
aws configure list-profiles
```

2. To check active profle
```agsl
aws sts get-caller-identity
```
3. To check access and secret key of profile
```agsl
cat $HOME/.aws/credentials
```

4. To set profile from one profile to another
```agsl
export AWS_PROFILE=[Profile Name]
```

By default if we do not set any profile 
it will check aws access chain provider and choose default profile.


5 . How to up localstack on your local
```
docker run \
> --rm -it \
> -p 4566:4566 \
> -p 4510-4559:4510-4559 \
> localstack/localstack

```
6 . To set properties file active set environment variable
```agsl
SPRING_PROFILES_ACTIVE=local
```
7. How to check profile in .aws/credetials on local
```agsl
vi $HOME/.aws/credentials
```
8. To check list of s3 buckets on aws cli
```agsl
aws s3api list-buckets --endpoint http://localhost:4566
```