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